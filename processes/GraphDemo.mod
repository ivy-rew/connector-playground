[Ivy]
1712BC4635081B26 7.5.0 #module
>Proto >Proto Collection #zClass
Go0 GraphDemo Big #zClass
Go0 B #cInfo
Go0 #process
Go0 @TextInP .type .type #zField
Go0 @TextInP .processKind .processKind #zField
Go0 @TextInP .xml .xml #zField
Go0 @TextInP .responsibility .responsibility #zField
Go0 @StartRequest f0 '' #zField
Go0 @EndTask f1 '' #zField
Go0 @RestClientCall f3 '' #zField
Go0 @PushWFArc f4 '' #zField
Go0 @PushWFArc f2 '' #zField
Go0 @EndRequest f6 '' #zField
Go0 @ErrorBoundaryEvent f8 '' #zField
Go0 @PushWFArc f9 '' #zField
>Proto Go0 Go0 GraphDemo #zField
Go0 f0 outLink start.ivp #txt
Go0 f0 inParamDecl '<> param;' #txt
Go0 f0 requestEnabled true #txt
Go0 f0 triggerEnabled false #txt
Go0 f0 callSignature whoami() #txt
Go0 f0 caseData businessCase.attach=true #txt
Go0 f0 showInStartList 1 #txt
Go0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>whoami.ivp</name>
    </language>
</elementInfo>
' #txt
Go0 f0 @C|.responsibility Everybody #txt
Go0 f0 81 49 30 30 -25 17 #rect
Go0 f0 @|StartRequestIcon #fIcon
Go0 f1 337 49 30 30 0 15 #rect
Go0 f1 @|EndIcon #fIcon
Go0 f3 clientId 28e1d26d-a492-401f-9620-64210dc91f7a #txt
Go0 f3 path /me #txt
Go0 f3 templateParams 'api.version="v1.0";
' #txt
Go0 f3 resultType com.microsoft.graph.models.extensions.User #txt
Go0 f3 responseCode 'out.me = result;

ivy.log.info("got MS user: "+out.me);' #txt
Go0 f3 clientErrorCode ivy:error:rest:client #txt
Go0 f3 statusErrorCode ivy:error:rest:client #txt
Go0 f3 168 42 112 44 0 -7 #rect
Go0 f3 @|RestClientCallIcon #fIcon
Go0 f4 111 64 168 64 #arcP
Go0 f2 280 64 337 64 #arcP
Go0 f6 337 145 30 30 0 15 #rect
Go0 f6 @|EndRequestIcon #fIcon
Go0 f8 actionTable 'out=in;
out.code=error.getAttribute("deviceCode") as com.microsoft.aad.msal4j.DeviceCode;
' #txt
Go0 f8 errorCode ms:graph:usertoken #txt
Go0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ms:graph:usertoken</name>
    </language>
</elementInfo>
' #txt
Go0 f8 81 145 30 30 0 15 #rect
Go0 f8 @|ErrorBoundaryEventIcon #fIcon
Go0 f9 111 160 337 160 #arcP
>Proto Go0 .type connect.ms.Data #txt
>Proto Go0 .processKind NORMAL #txt
>Proto Go0 0 0 32 24 18 0 #rect
>Proto Go0 @|BIcon #fIcon
Go0 f0 mainOut f4 tail #connect
Go0 f4 head f3 mainIn #connect
Go0 f3 mainOut f2 tail #connect
Go0 f2 head f1 mainIn #connect
Go0 f8 mainOut f9 tail #connect
Go0 f9 head f6 mainIn #connect
