package bg.softuni.pathfinder.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
public class UsersPK implements Serializable {
    private long id;

    private String username;
}
