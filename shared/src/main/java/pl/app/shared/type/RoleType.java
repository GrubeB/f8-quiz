package pl.app.shared.type;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

public enum RoleType implements Includable<RoleType> {
    STUDENT,
    TUTOR ,
    ADMIN,
    GOD;

    private final Set<RoleType> children = new HashSet<>();
    private final Set<PermissionType> permissions = new HashSet<>();

    static {
        initRoleChildren();
        initRolePermissions();
    }

    public Set<PermissionType> getPermissions() {
        return Collections.unmodifiableSet(permissions);
    }
    public void setPermissions(Set<PermissionType> newPermissions) {
        permissions.clear();
        permissions.addAll(newPermissions);
    }

    @Override
    public boolean include(RoleType type) {
        return this.equals(type) || children.stream().anyMatch(t -> t.include(type));
    }

    @Component("RoleType")
    @Getter
    static class SpringComponent {
        private final RoleType STUDENT = RoleType.STUDENT;
        private final RoleType TUTOR = RoleType.TUTOR;
        private final RoleType ADMIN = RoleType.ADMIN;
        private final RoleType GOD = RoleType.GOD;
    }
    private static void initRoleChildren() {
        TUTOR.children.add(STUDENT);
        ADMIN.children.add(TUTOR);

        GOD.children.addAll(Arrays.stream(RoleType.values()).toList());
    }
    private static void initRolePermissions() {
        STUDENT.permissions.addAll(List.of(
                PermissionType.USER_VIEWER,
                PermissionType.USER_MANAGEMENT));

        GOD.permissions.add(PermissionType.GOD);

        // add all permissions from children
        Arrays.stream(values())
                .forEach(roleType -> {
                    Set<PermissionType> permissionTypes = roleType.children.stream()
                            .map(RoleType::getPermissions)
                            .flatMap(Set::stream)
                            .collect(Collectors.toSet());
                    permissionTypes.addAll(roleType.permissions);
                    roleType.setPermissions(permissionTypes);
                });
    }

}
