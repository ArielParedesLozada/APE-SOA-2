import { Request, Response } from "express";
import VehiculoUseCases from "../../../domain/use-cases/vehiculos";
import { Vehiculo } from "../../../domain/entities/vehiculo.entity";

export class VehiculoController {
    constructor() {
    }

    getAll = async (req: Request, res: Response) => {
        try {
            const usecase = new VehiculoUseCases.CRUD()
            const vehiculos = await usecase.getAll()
            res.json(vehiculos)
        } catch (error) {
            console.error(error)
            res.json(error)
        }
    }

    getOne = async (req: Request, res: Response) => {
        try {
            const { id } = req.params
            const _id = parseInt(id)
            const usecase = new VehiculoUseCases.CRUD()
            const vehiculo = await usecase.get(_id)
            res.json(vehiculo)
        } catch (error) {
            console.error(error)
            res.json(error)
        }
    }

    create = async (req: Request, res: Response) => {
        try {
            const data: Vehiculo = req.body
            const usecase = new VehiculoUseCases.CRUD()
            const vehiculos = await usecase.create(data)
            res.json(vehiculos)
        } catch (error) {
            console.error(error)
            res.json(error)
        }
    }

    update = async (req: Request, res: Response) => {
        try {
            const { id } = req.params
            const _id = parseInt(id)
            const usecase = new VehiculoUseCases.CRUD()
            const vehiculos = await usecase.get(_id)
            res.json(vehiculos)
        } catch (error) {
            console.error(error)
            res.json(error)
        }
    }

    delete = async (req: Request, res: Response) => {
        try {
            const usecase = new VehiculoUseCases.CRUD()
            const vehiculos = await usecase.getAll()
            res.json(vehiculos)
        } catch (error) {
            console.error(error)
            res.json(error)
        }
    }

}