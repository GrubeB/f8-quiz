package pl.app.shared.type;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum PermissionType implements Includable<PermissionType> {
    ROLE_VIEWER,
    ROLE_MANAGEMENT,
    USER_VIEWER,
    USER_MANAGEMENT,
    GOD;

    private final Set<PermissionType> children = new HashSet<>();

    static {
        USER_MANAGEMENT.children.add(USER_VIEWER);
        GOD.children.addAll(Arrays.stream(PermissionType.values()).toList());
    }

    @Override
    public boolean include(PermissionType type) {
        return this.equals(type) || children.stream().anyMatch(t -> t.include(type));
    }

    @Component("PermissionType")
    @Getter
    static class SpringComponent {
        private final PermissionType USER_VIEWER = PermissionType.USER_VIEWER;
        private final PermissionType USER_MANAGEMENT = PermissionType.USER_MANAGEMENT;
        private final PermissionType GOD = PermissionType.GOD;
    }

}
