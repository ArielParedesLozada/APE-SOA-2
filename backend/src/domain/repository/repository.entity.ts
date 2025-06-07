import { IDatabaseRepository } from "../../data/repository.datasource";
import { IMapper } from "../../infraestructure/mapper/mapper.abstract";

export class EntityRepository<Entity> {
    constructor(
        private readonly datasource: IDatabaseRepository<any>,
        private readonly mapper: IMapper<Entity>
    ) {
    }
    public async findAll(relations?: string[]): Promise<Entity[]> {
        const models = await this.datasource.findAll(relations)
        return models.map(this.mapper.toDomain)
    }
    public async findById(id: number, relations?: string[]): Promise<Entity | null> {
        const model = await this.datasource.findById(id, relations)
        return model ? this.mapper.toDomain(model) : null
    }
    public async create(created: Entity): Promise<boolean> {
        const entity = this.mapper.toModel(created)
        return this.datasource.create(entity)
    }
    public async update(updated: Entity): Promise<boolean> {
        const entity = this.mapper.toModel(updated)
        return this.datasource.update(entity)

    }
    public async delete(deleted: Entity): Promise<boolean> {
        const entity = this.mapper.toModel(deleted)
        return this.datasource.delete(entity)
    }
}