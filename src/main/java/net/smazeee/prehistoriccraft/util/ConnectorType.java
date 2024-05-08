package net.smazeee.prehistoriccraft.util;

import net.minecraft.util.StringRepresentable;

public enum ConnectorType implements StringRepresentable {
    NONE,
    CABLE,
    BLOCK;

    public static final ConnectorType[] VALUES = values();

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
