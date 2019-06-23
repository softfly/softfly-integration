package pl.softfly.integ.doc.repository;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import pl.softfly.integ.doc.entity.DocumentBody;
import pl.softfly.integ.doc.entity.DocumentHeader;


/**
 * Repository.
 *
 * @author Grzegorz Ziemski
 */
public class DocumentHeaderRepositoryBean {

  public List<DocumentBody> findCurrent(DocumentHeader documentHeader) {
    OptionalInt current = documentHeader.getBodies()//
        .stream()//
        .mapToInt(DocumentBody::getVersion)//
        .max();
    if (current.isPresent()) {
      return documentHeader.getBodies()//
          .stream()//
          .filter(p -> p.getVersion().equals(current.getAsInt()))//
          .collect(Collectors.toList());
    } else {
      return null;
    }
  }

}
