package pl.softfly.integ.samples.invoice.jaxws.doc.validation.schema;

import pl.softfly.integ.doc.entity.DocumentHeader;

public class DocumentValidationSchemaBean implements
    pl.softfly.integ.doc.validation.schema.DocumentValidationSchema {

  private DocumentValidationSchema documentValidationSchema = new DocumentValidationSchema();

  private DocumentValidationSchemaService documentValidationSchemaService =
      documentValidationSchema.getDocumentValidationSchemaServicePort();

  @Override
  public DocumentHeader validate(DocumentHeader documentHeader) {
    return documentValidationSchemaService.validate(documentHeader);
  }
}
