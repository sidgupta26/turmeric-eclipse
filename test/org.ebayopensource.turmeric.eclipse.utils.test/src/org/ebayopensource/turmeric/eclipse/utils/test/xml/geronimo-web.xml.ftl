<?xml version="1.0" encoding="UTF-8"?>
<!--
    Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
    Licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 
    You may obtain a copy of the License at 
   
       http://www.apache.org/licenses/LICENSE-2.0
       
 -->
<web-app xmlns="http://geronimo.apache.org/xml/ns/j2ee/web-1.1" xmlns:nam="http://geronimo.apache.org/xml/ns/naming-1.1" xmlns:sec="http://geronimo.apache.org/xml/ns/security-1.1" xmlns:sys="http://geronimo.apache.org/xml/ns/deployment-1.1">
  <sys:environment>
    <sys:moduleId>
      <sys:groupId>org.ebayopensource.turmeric</sys:groupId>
      <sys:artifactId>${serviceName}</sys:artifactId>
      <sys:version>1.0.0</sys:version>
      <sys:type>war</sys:type>
    </sys:moduleId>
    <sys:dependencies>
      <sys:dependency>
        <sys:groupId>geronimo</sys:groupId>
        <sys:artifactId>sharedlib</sys:artifactId>
        <sys:type>car</sys:type>
      </sys:dependency>
    </sys:dependencies>
  </sys:environment>
  <context-root>/${serviceName}</context-root>
</web-app>