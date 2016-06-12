package jenky.codebuddy.services;

import jenky.codebuddy.models.rest.ActiveProjects;
import jenky.codebuddy.models.rest.SingleProject;

/**
 * Created by Fabian on 12-6-2016.
 */
public interface UserProjectsService {
    /**
     *
     * @param token
     * @return List of active projects
     */
    ActiveProjects returnActiveProjectsForUser(String token);

    /**
     *
     * @param projectId
     * @return singe project
     */
    SingleProject returnSingleProjectWithScores(int projectId);
}
