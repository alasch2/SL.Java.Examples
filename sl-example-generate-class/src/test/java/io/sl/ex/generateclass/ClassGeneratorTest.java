package io.sl.ex.generateclass;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import org.junit.Test;

public class ClassGeneratorTest {

    @Test
    public void testClassGeneration() {
//        List<Exception> exceptions = generateErrors(0, 20);
//        assertTrue("Not enough exception was generated", exceptions.size() > 5);
        Class clazz = null;
        try {
            clazz = generateClass(1);
            if (clazz == null) {
                throw new RuntimeException("Error generating class");
            }
            ((Runnable) clazz.newInstance()).run();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        assertNotNull("Class not created", clazz);
    }

    public List<Exception> generateErrors(int start, int amount) {
        List<Exception> exceptions = new LinkedList<Exception>();
        for (int i = start; i < start + amount; i++) {
            Class clazz;
            try {
                clazz = generateClassWithException(i);
                if (clazz == null) {
                    throw new RuntimeException("Error generating class");
                }
                ((Runnable) clazz.newInstance()).run();
            } catch (ArrayIndexOutOfBoundsException e) {
                exceptions.add(e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return exceptions;
    }

    private String sourceOfClass(String className) {
        return String.format("public class %s implements Runnable{\n", className) +
                "    @Override" +
                "    public void run() {\n" +
                "        int[] temp = new int[] {1, 2, 3};\n" +
                "        System.out.println(\"running \" + getClass().getName());\n" +
                "    }\n" +
                "}\n";
    }

    private String sourceOfClassWithException(String className) {
        return String.format("public class %s implements Runnable{\n", className) +
                "    @Override" +
                "    public void run() {\n" +
                "        int[] temp = new int[] {1, 2, 3};\n" +
                "        temp[3] += 10;\n" +
                "        System.out.println(\"running \" + getClass().getName());\n" +
                "    }\n" +
                "}\n";
    }

    private Class generateClass(int num) throws ClassNotFoundException{
        String className = String.format("GeneratedClass%d", num);
        String sourceCode = sourceOfClass(className);
        return generateClass(className, sourceCode);
    }

    private Class generateClassWithException(int num) throws ClassNotFoundException{
        String className = String.format("GeneratedClassWithException%d", num);
        String sourceCode = sourceOfClassWithException(className);
        return generateClass(className, sourceCode);
    }

    private Class generateClass(String className, String sourceCode) throws ClassNotFoundException {
        JavaCompiler compiler;
        compiler = ToolProvider.getSystemJavaCompiler();

        JavaFileObject compilationUnit =
                new StringJavaFileObject(className, sourceCode);

        SimpleJavaFileManager fileManager =
                new SimpleJavaFileManager(compiler.getStandardFileManager(null, null, null));

        JavaCompiler.CompilationTask compilationTask = compiler.getTask(
                null, fileManager, null, null, null, Arrays.asList(compilationUnit));

        if (compilationTask.call()) {

            CompiledClassLoader classLoader =
                    new CompiledClassLoader(fileManager.getGeneratedOutputFiles());
            return classLoader.loadClass(className);
        }
        return null;
    }

    private class StringJavaFileObject extends SimpleJavaFileObject {
        private final String code;

        public StringJavaFileObject(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension),
                    Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return code;
        }
    }

    private class ClassJavaFileObject extends SimpleJavaFileObject {
        private final ByteArrayOutputStream outputStream;
        private final String className;

        protected ClassJavaFileObject(String className, JavaFileObject.Kind kind) {
            super(URI.create("mem:///" + className.replace('.', '/') + kind.extension), kind);
            this.className = className;
            outputStream = new ByteArrayOutputStream();
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return outputStream;
        }

        public byte[] getBytes() {
            return outputStream.toByteArray();
        }

        public String getClassName() {
            return className;
        }
    }

    private class SimpleJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
        private final List<ClassJavaFileObject> outputFiles;

        protected SimpleJavaFileManager(JavaFileManager fileManager) {
            super(fileManager);
            outputFiles = new ArrayList<ClassJavaFileObject>();
        }

        @Override
        public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws
                IOException {
            ClassJavaFileObject file = new ClassJavaFileObject(className, kind);
            outputFiles.add(file);
            return file;
        }

        public List<ClassJavaFileObject> getGeneratedOutputFiles() {
            return outputFiles;
        }
    }

    private class CompiledClassLoader extends ClassLoader {
        private final List<ClassJavaFileObject> files;

        private CompiledClassLoader(List<ClassJavaFileObject> files) {
            this.files = files;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            Iterator<ClassJavaFileObject> itr = files.iterator();
            while (itr.hasNext()) {
                ClassJavaFileObject file = itr.next();
                if (file.getClassName().equals(name)) {
                    itr.remove();
                    byte[] bytes = file.getBytes();
                    return super.defineClass(name, bytes, 0, bytes.length);
                }
            }
            return super.findClass(name);
        }
    }

}
