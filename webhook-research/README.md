# webhook-research
## ngrok tunnelling

Open terminal from the project root and start ngrok :

    node_modules\.bin\ngrok http 3000

The port is a value 'server.port' from application.properties file.    

The ngrok outputs the url, that should be used by webhook .

The ngrok free version stops in a couple of hours and should be restarted again. The wbehook should be updated after the ngrok restart with new url.
## webhook application
Open terminal from the project root and start application :

    mvn spring-boot:run
    
# API-s
###server - TODO
### cloud
1. XRay rest - is no relevant
2. GraphQL - to execute CRUD on XRay issues

## GraphQL API
### jwt token      
GraphQL demands jwt token for any query/mutation.
Create a token with postman 
url:
     
    https://xray.cloud.xpand-it.com/api/v2/authenticate

body: 
      
    {"client_id":"2AB958DD1A764F9794025A95011AD016", "client_secret":"e6040648b8acd6866b28fd510446ed9864179e723d541a1568314f06ef99b659"}

### graphQL queries
1. GrapgQLPlayground - download and install.
2. Open workspace with URL https://xray.cloud.xpand-it.com/api/v2/graphql.
3. Add AUthorization header with a bearer token value, returned by postman as a Json:

    {"Authorization" : "Bearer <token from postman>"}
    

GrapgQLPlayground allows usage of "native" query language and has the in-line documentation and examples

The same requests may be done from postman with "raw" Json format

GraphQL format example:
    
    query{getTestExecution(issueId:"11010"){issueId}}

Raw Json format for the same:

    {"query":"{getTestExecution(issueId:\"11010\"){issueId}}"}
## Jira RestAPI
The jira RestAPI may be used for both cloud and server - returns any issue data, but it doesn't returns XRay issues relations, so is not good enough. 

### Authentication
Create API account on atlasian account and use it for API-s

Link to token creation: 

    https://id.atlassian.com/manage-profile/security/api-tokens

Use basic authorization in each request:

    Header: Basic Auth
    UserName: ala.schneider@sealights.io (or other)
    Password: Pe0ZwrV8Yd9oWE5xFGis68D0 (this is API token)
    
Example of getting issue:

    https://sealightsdevelop.atlassian.net/rest/api/2/issue/SLDEV-8197

Getting an issue may is helpful for having "names" for jira fields in the GrapgQL query 
# Jira links
## Customize fields
Go to ProjetSettings->Fields (on the left menu).
On the rights clock on Actions iscon and select Edit fields. This will open the url:
    
    https://sealightsdevelop.atlassian.net/secure/admin/ViewCustomFields.jspa
## Webhooks

    https://sealightsdevelop.atlassian.net/plugins/servlet/webhooks
## Adding Test Status
To reach TestStatuses settings:
   1. Click settings icon on the top-right and select Apps.
   2. On the left appear XRay extended settings. Select Test Statuses

    https://sealightsdevelop.atlassian.net/plugins/servlet/ac/com.xpandit.plugins.xray/xray-global-settings-test-statuses?s=com.xpandit.plugins.xray__xray-global-settings-test-statuses

