# Softfly Integration
[![Build Status](https://travis-ci.com/softfly/softfly-integration.svg?branch=master)](https://travis-ci.com/softfly/softfly-integration)
[![Coverage Status](https://coveralls.io/repos/github/softfly/softfly-integration/badge.svg?branch=master)](https://coveralls.io/github/softfly/softfly-integration?branch=master)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pl.softfly.integ%3Asoftfly-integ&metric=alert_status)](https://sonarcloud.io/dashboard?id=pl.softfly.integ%3Asoftfly-integ)
<br/>
Services (beans) for repetitive and common functions of integration platforms.

#### Links:
https://softfly.github.io/softfly-integration/

#### Services (beans):
* [IncomingShipment](/softfly-integ-core/src/main/java/pl/softfly/integ/shipment/in)<br/>
Create IncomingShipment with details from where it came from.
* [DocumentRecognizeFormat](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/recognize)<br/>
Recognize the document format e.g. XML, EDIFACT, Invoice.
* [DocumentValidationSchema](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/validation/schema)<br/>
Check if the document has the correct message structure e.g. XML, EDI.
* [DocumentValidationBusiness](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/validation/business)<br/>
Check if the document is correct with business validation rules e.g. REGEX of id.
* [Parser](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/parser)<br/>
Parse the text of message to POJO.
* [Transformation](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/transformation)<br/>
Transform the document to other format.
* [OutgoingShipment](/softfly-integ-core/src/main/java/pl/softfly/integ/shipment/out)<br/>
Determine the outgoing shipment (endpoints, recipient) to which the document should be sent.
* [SenderEndpoint](/softfly-integ-core/src/main/java/pl/softfly/integ/endpoint)<br/>
Send and receive messages via endpoints.

## Objective
* An ideally run project that can be used as a model for other projects.
  * Clean code.
  * Clean, short and concise documentation closely related and mapping specific areas of the code.
* Comparison of different technologies for the same use cases.
* Base code for articles.

## Project plan
* __Design a skeleton of classes and interfaces that simulate happy flow in Java SE__.
* Add Spring Boot.
* Add Spring Web Services.
* Add JPA.
* Add NoSQL.
* Deploy in cloud providers e.g. AWS, OpenShift.
* Add performance tests.
* Add large file support by streaming.
* Add JBPM.
* Deploy on Aplication Server e.g. Wildfly.
* Implement services.
* Add integration test framework.
* Add artificial intelligence to inference and adding business rules to services from integration testing.

## Examples of projects
### 1. [Invoice Integration](/softfly-integ-samples/softfly-integ-samples-invoice)
The example application that transmits an invoice from the seller to the buyer.

#### 1.1 [Example of Business Flow](softfly-integ-samples/softfly-integ-samples-invoice/softfly-integ-samples-invoice-java/src/main/java/pl/softfly/integ/samples/invoice/flows/ForwardDocumentFlowBean.java)
|#|Step|Service|
|-|-|-|
||Receive the document from the endpoint.|[IncomingShipment](/softfly-integ-core/src/main/java/pl/softfly/integ/shipment/in)|
|1|Recognize the document format.|[DocumentRecognizeFormat](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/recognize)|
|2|Validate schema of the document.|[DocumentValidationSchema](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/validation/schema)|
|3|(Optional) Transform to the supported document format by DocumentValidationBusiness.|[Transformation](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/transformation)|
|4|Validate business validation of the document.|[DocumentValidationBusiness](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/validation/business)|
|5|Parse the document to POJO (recipients).|[Parser](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/parser)|
|...|Other business steps. (Changes in the content of the document.)||
|6|Determine the shipment (endpoints, recipient) to which the document should be sent.|[OutgoingShipment](/softfly-integ-core/src/main/java/pl/softfly/integ/shipment/out)|
|7|Generate the document from POJO.|[Transformation](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/transformation) or Formatter|
|8|(Optional) Transform to the recipient's document format.|[Transformation](/softfly-integ-core/src/main/java/pl/softfly/integ/doc/transformation)|
|9|Send the document.|[SenderEndpoint](/softfly-integ-core/src/main/java/pl/softfly/integ/endpoint)|

## TODOs
* Divide the project into microservices after the skeleton design phase. (Prototyping is faster on a single project.)