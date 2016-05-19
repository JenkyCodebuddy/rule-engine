package jenky.codebuddy.models.databaseModels;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Fabian on 19-5-2016.
 */
public class Company {

    @Id
    @GeneratedValue
    private String id;

    @OneToMany
    @JoinColumn(name = "score_id")
    private project Project;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "deleted_at")
    private Date deleted_at;


}
