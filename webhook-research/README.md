# Webhook
## ngrok tunnelling

Open terminal from the project root and start ngrok :

    node_modules\.bin\ngrok http [port]

The port is a value 'server.port' from application.properties file.   

The ngrok outputs the url, that should be used by webhook .

The ngrok free version stops in a couple of hours and should be restarted again. The webhook should be updated after the ngrok restart with new url.
## webhook spring application
Open terminal from the project root and start application :

    mvn spring-boot:run
    
## Webhook definition on Jira
The webhook may be added manually, once a Jira user has admin permissions:
- Open settings (a wheel on the top-right of the menu) -> Select `System`
- On the left bar find `ADVANCED` and select `Webhooks`.

The direct link to webhooks (example): https://sealightsdevelop.atlassian.net/plugins/servlet/webhooks

Create a webhook with properties: 
- URL - `https://[generated-by-ngrok].ngrok.io/sl/execution/webhook`
- JQL: `issuetype in ("Test Execution") AND status changed to ("Start", "End")`
- Trigger: `issue updated`
- Exclude body: `no`

# API-s for cloud
- GraphQL - should be used for CRUD on XRay entities (TestExecution, TestRun). See more https://docs.getxray.app/display/XRAYCLOUD/API
- Jira RestAPI - for CRUD on TestExecution issue (set SL_LAB_ID) and custom field stuff.

## GraphQL
### GraphQLAPI jwt token 
GraphQL demands jwt token for any query/mutation.
- Prepare `client_id` and `client_secret` from XRay settings:
  - Open settings (a wheel on the top-right of the menu) -> Select `Apps`
  - On the left bar select `API Keys` - copy from there `client_id` and `client_secret`

- Create a token with postman 
   - url: https://xray.cloud.xpand-it.com/api/v2/authenticate
   - body: 
      
         {"client_id":"2AB958DD1A764F9794025A95011AD016", "client_secret":"e6040648b8acd6866b28fd510446ed9864179e723d541a1568314f06ef99b659"}
(I used here my values)

### GraphQLPlayground
GraphQLPlayground allows usage of "native" query language and has the in-line documentation and examples.
1. GraphQLPlayground - download and install.
2. Open workspace - URL https://xray.cloud.xpand-it.com/api/v2/graphql.
3. Create a token with postman (as explained earlier)
3. Add Authorization header with a bearer token value, returned by Postman as a Json:

       {"Authorization" : "Bearer <token from postman>"}
    
The GraphQL requests may be done from postman in "raw" Json format

GraphQL format example:
    
    query{getTestExecution(issueId:"11010"){issueId}}

Raw Json format for the same to be used in Postman:

    {"query":"{getTestExecution(issueId:\"11010\"){issueId}}"}

### GraphQL Documentation
- https://docs.getxray.app/display/XRAYCLOUD/GraphQL+API
- https://xray.cloud.xpand-it.com/doc/graphql/teststatustype.doc.html - queries and mutations examples (easier to use than on GraphQLPlayground)

## Jira RestAPI
The jira RestAPI returns any issue data, but it doesn't returns XRay issues relations, so is not good enough. 
Yet it is needed for the custom field SL_LAB_ID creation and update.

Reference: https://developer.atlassian.com/cloud/jira/platform/rest/v3/intro/
### Authentication
Create API account on atlassian account and use it for API-s

Link to token creation: 

    https://id.atlassian.com/manage-profile/security/api-tokens

Use basic authorization in each request:

    Header: Basic Auth
    UserName: ala.schneider@sealights.io (or other)
    Password: Pe0ZwrV8Yd9oWE5xFGis68D0 (this is API token)
    
Example of getting issue:

    https://sealightsdevelop.atlassian.net/rest/api/2/issue/SLDEV-8197

# Jira Manual Settings Memo
## Adding Test Status Procedure
To reach TestStatuses settings:
   1. Click settings icon on the top-right and select Apps.
   2. On the left appear XRay extended settings. Select Test Statuses

    https://sealightsdevelop.atlassian.net/plugins/servlet/ac/com.xpandit.plugins.xray/xray-global-settings-test-statuses?s=com.xpandit.plugins.xray__xray-global-settings-test-statuses

