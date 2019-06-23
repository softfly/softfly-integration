package pl.softfly.integ.doc.recognize;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentBusinessType;
import pl.softfly.integ.doc.entity.DocumentFormat;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * This logic is not in service for reduce the amount of unnecessary data being transported. Often
 * repeated logic.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentRecognizeFormatUtil {

  /**
   * Enrich the document with the returned object from {@link DocumentRecognizeFormat#recognize}.
   */
  public static boolean enrichRecognize(DocumentRecognizeFormat documentRecognize,
      DocumentHeader documentHeader) {
    Objects.requireNonNull(documentHeader);

    boolean isRecognize = false;
    for (DocumentBody documentBody : documentHeader.getBodies()) {
      documentBody.setDocumentFormat(documentRecognize.recognize(documentBody.getBody()));

      if (documentBody.getDocumentFormat() != null) {
        addAllDocumentBusinessType(documentHeader, documentBody);
        isRecognize = true;
      }
    }

    return isRecognize;
  }

  /**
   * Append of {@link DocumentBusinessType} from {@link DocumentBody} to {@link DocumentHeader}.
   */
  protected static void addAllDocumentBusinessType(DocumentHeader documentHeader,
      DocumentBody documentBody) {
    DocumentFormat documentFormat = documentBody.getDocumentFormat();
    if (Objects.nonNull(documentFormat)) {

      DocumentBusinessType documentBusinessType = documentFormat.getDocumentBusinessType();
      if (Objects.nonNull(documentBusinessType)) {

        Set<DocumentBusinessType> documentBusinessTypes;
        if (CollectionUtils.isNotEmpty(documentHeader.getDocumentBusinessType())) {
          documentBusinessTypes = documentHeader.getDocumentBusinessType();
        } else {
          documentBusinessTypes = new HashSet<>();
          documentHeader.setDocumentBusinessType(documentBusinessTypes);
        }

        documentBusinessTypes.add(documentBusinessType);
      }
    }
  }

}
