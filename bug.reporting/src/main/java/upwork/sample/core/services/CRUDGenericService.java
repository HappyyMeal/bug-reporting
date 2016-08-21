package upwork.sample.core.services;

import java.util.List;

public interface CRUDGenericService<Entity> {

    public Entity create(Entity entity);
    public Entity read(int entityId);
    public Entity update(Entity entity);
    public Entity delete(int entityId);
}
