import { DatasourceFactory } from "../../../infraestructure/datasource/datasource.factory";
import { GlobalDatabase } from "../../../infraestructure/datasource/datasource.global";
import { EntityRepository } from "../../repository/repository.entity";
import { CustomError } from "../../entities/error.entity";
import { Vehiculo } from "../../entities/vehiculo.entity";
import { VehiculoMapper } from "../../../infraestructure/mapper/vehiculo.mapper";
import { VehiculoModel } from "../../../data/models/vehiculo.model";

export class CRUD {
    private readonly repository: EntityRepository<Vehiculo>

    constructor() {
        const database = GlobalDatabase.getInstance().database
        const datasource = DatasourceFactory.generateRepository(database, VehiculoModel)
        if (!datasource) {
            throw new Error("Repositorio no implementado")
        }
        const mapper = new VehiculoMapper()
        this.repository = new EntityRepository<Vehiculo>(datasource, mapper)
    }
    public getAll(): Promise<Vehiculo[]> {
        try {
            const result = this.repository.findAll(['marca', 'modelo', 'color'])
            return result
        } catch (error) {
            throw new CustomError(400, "Error al cargar los vehiculos")
        }
    }

    public get(id: number): Promise<Vehiculo | null> {
        try {
            const result = this.repository.findById(id, ['marca', 'modelo', 'color'])
            if (!result) {
                throw new CustomError(400, "No encontrado")
            }
            return result
        } catch (error) {
            throw new CustomError(400, "No encontrado")
        }
    }

    public create(created: Vehiculo): Promise<boolean> {
        try {
            const result = this.repository.create(created)
            return result
        } catch (error) {
            throw new CustomError(400, "No se creo")
        }
    }

    public update(updated: Vehiculo): Promise<boolean> {
        try {
            const result = this.repository.update(updated)
            return result
        } catch (error) {
            throw new CustomError(400, "No se actualizo")
        }
    }

    public delete(deleted: Vehiculo): Promise<boolean> {
        try {
            const result = this.repository.delete(deleted)
            return result
        } catch (error) {
            throw new CustomError(400, "No se borro")
        }
    }
}