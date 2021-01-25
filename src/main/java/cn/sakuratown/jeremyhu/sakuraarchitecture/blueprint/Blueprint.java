package cn.sakuratown.jeremyhu.sakuraarchitecture.blueprint;

import cn.sakuratown.jeremyhu.sakuraarchitecture.selection.Selection;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Blueprint {

    private final Player creator;
    private final String uuid;
    private final Selection selection;

    public Blueprint(Builder<?> builder) {
        this.creator = builder.creator;
        this.uuid = builder.uuid;
        this.selection = builder.selection;
    }

    protected abstract static class Builder<T extends Builder<T>> {
        private Player creator;
        private final String uuid = UUID.randomUUID().toString();
        private Selection selection;

    }
}
