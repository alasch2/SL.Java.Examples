package io.sealights.onpremise.slmock.agent.instrument;


import io.sealights.onpremise.slmock.agent.dto.ClassDiscoveryDTO;
import io.sealights.onpremise.slmock.agent.dto.MethodInfoDTO;
import io.sealights.onpremise.slmock.agent.dto.RouteInfoDTO;
import io.sealights.onpremise.slmock.agent.instrument.spring.SpringRequestMappingAnalyzer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ApiRepository { //extends MethFootprintsCollector<APAFootprintsContainer, APAMethodFootprint> {
    private Map<String, MethodInfoDTO> routeToMethod = new HashMap<>();
    private Set<String> analyzedEndpoints = new HashSet<>();


    private static ApiRepository instance;

    public ApiRepository() {
        super();
        instance = this;
    }

    public static void exploreApiCall(Object apiCall, String methodId) {
        try {
            if (instance == null)
                new ApiRepository();
            instance.discoverClassRoutesAndHitMethod(apiCall, methodId);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static MethodInfoDTO getMethodInfoDTO(String path, String httpMethod) {
        if (instance == null)
            new ApiRepository();
        return instance.getMethodInfoByRoute(path, httpMethod);

    }

    protected void discoverClassRoutesAndHitMethod(Object apiCall, String methodId) {
        String className = apiCall.getClass().getName();
        String key = className + ":" + methodId;
        if (analyzedEndpoints.contains(key)){
            System.out.println("No need to detected routes for " + className);
            return;
        }


        System.out.println("Detecting api routes of " + className);
        ClassDiscoveryDTO dto = new ClassDiscoveryDTO(apiCall.getClass());

        exploreSpringMapping(dto, methodId);
    }

    protected void exploreSpringMapping(ClassDiscoveryDTO dto, String methodId) {
        SpringRequestMappingAnalyzer explorer = new SpringRequestMappingAnalyzer(dto);
        dto = explorer.execute();
        if (!dto.isEmpty()) {
            Map<String, RouteInfoDTO> routes = dto.getRoutes();
            for (String key : routes.keySet()) {
                RouteInfoDTO route = routes.get(key);
                String key2 = getRouteKey(route.getPath(), route.getHttpMethod());
                routeToMethod.putIfAbsent(key2, new MethodInfoDTO(dto.getClassName(), route.getMethodName()));
                key2 = dto.getClassName() + ":" + methodId;
                analyzedEndpoints.add(key2);
            }

        }
        System.out.println(String.format("Class '%s': routes were added to footprints container", dto.getClassName()));
    }

    private String getRouteKey(String path, String httpMethod) {
        return String.format("'%s':'%s', ", path, httpMethod);
    }

    private MethodInfoDTO getMethodInfoByRoute(String path, String httpMethod) {
        String key = getRouteKey(path, httpMethod);
        return routeToMethod.get(key);
    }

}
