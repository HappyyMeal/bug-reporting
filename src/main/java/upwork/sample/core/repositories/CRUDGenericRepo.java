package upwork.sample.core.repositories;

import java.util.List;

public interface CRUDGenericRepo<Entity> {

    public Entity create(Entity entity);
    public Entity read(int entityId);
    public List<Entity> readAll();
    public Entity update(Entity entity);
    public Entity delete(int entityId);
}
