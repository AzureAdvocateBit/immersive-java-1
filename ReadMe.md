# Immersive Reader - Java Sample

## Prerequisites

* An Immersive Reader resource configured for Azure Active Directory authentication. Follow [these instructions](https://docs.microsoft.com/azure/cognitive-services/immersive-reader/how-to-create-immersive-reader?WT.mc_id=immersivejava-github-ropreddy) to get set up. You will need some of the values created here when configuring the sample project properties. Save the output of your session into a text file for future reference.
* [Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Visual Studio Code With Java extension](https://code.visualstudio.com/docs/languages/java?WT.mc_id=immersivejava-github-ropreddy)

## Usage

* In the **Command line** field, enter **mvn com.microsoft.azure:azure-webapp-maven-plugin:1.9.1:config** to setup your azure instance.
* In the **Command line** field, enter **mvn package azure-webapp:deploy** to deploy to Azure

2. Add properties to application setting on [azure app setting](https://docs.microsoft.com/azure/app-service/configure-common?WT.mc_id=immersivejava-github-ropreddy)

    ```text
    TENANT_ID=<YOUR_TENANT_ID>
    CLIENT_ID=<YOUR_CLIENT_ID>
    CLIENT_SECRET<YOUR_CLIENT_SECRET>
    SUBDOMAIN=<YOUR_SUBDOMAIN>
    ```

3. Open a web browser and navigate to app server url

## License

Copyright (c) Microsoft Corporation. All rights reserved.

Licensed under the MIT License.
