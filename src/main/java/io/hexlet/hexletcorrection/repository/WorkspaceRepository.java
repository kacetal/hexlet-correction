package io.hexlet.hexletcorrection.repository;

import io.hexlet.hexletcorrection.domain.Workspace;
import io.hexlet.hexletcorrection.security.model.SecuredWorkspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, String> {

    Optional<SecuredWorkspace> getSecuredWorkspaceByName(final String name);
}
