# MS Teams as Terminal Chat

Get a lightweight client to stay in the loop with microsoft teams.

... experiment with MS Graph API

### Managing App
https://portal.azure.com/#blade/Microsoft_AAD_RegisteredApps/ApplicationMenuBlade/Overview/appId/0960abf3-45f9-4ba3-8975-e2b25a5178e3

### Idenitfied issues

- [ ] need perms to read all users from the company just list my channels: https://docs.microsoft.com/en-us/graph/api/user-list-joinedteams?view=graph-rest-1.0&tabs=http
- [ ] hard to interact with odata auth features from third party clients (such as postman). The only exception being is microsoft own tooling https://developer.microsoft.com/en-us/graph/graph-explorer#
- [x] custom stack for http auth request (MSAL4J) which relies upon commons http client and lots of other JARs. Altough, it should be possible to adapt the request from here: com.microsoft.aad.msal4j.DeviceCodeFlowRequest.acquireDeviceCode(String, String, Map<String, String>, ServiceBundle)
 - prooved that IHttpClient from MSAL4J can easily be replaced by "Jersey Delegating" impl. >> com.microsoft.auth.jersey.JerseyMsal4jClient
 - prooved that MSAL4J dependencies can be easily put on a diet by using ivy-core Http stack >> pom.xml

