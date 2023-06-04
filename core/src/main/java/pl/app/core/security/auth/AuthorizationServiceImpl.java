package pl.app.core.security.auth;


class AuthorizationServiceImpl implements AuthorizationService {
    public boolean hasAnyRoleByCommunityId(Long communityId, String... roles) {
        return false;
    }
}
