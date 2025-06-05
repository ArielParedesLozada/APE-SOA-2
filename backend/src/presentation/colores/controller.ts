import { Request, Response } from "express";
import { IRepository } from "../../data/repository.datasource";
import { ColorModel } from "../../data/models/color.model";

export class ColorController {
    constructor(
        private readonly repository: IRepository<ColorModel>
    ) {
    }
    getAll = async (req: Request, res: Response) => {
        try {
            const models = await this.repository.findAll();
            res.json(models);
        } catch (error) {
            console.error("Error al obtener los colores:", error);
            res.status(500).json({ error: "Error al obtener los colores" });
        }
    }
}