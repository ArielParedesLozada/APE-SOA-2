import { Router } from "express";
import { ColorRoutes } from "./routes/colores/routes";
import { VehiculoRoutes } from "./routes/vehiculos/routes";

export class AppRoutes {
    constructor() { }

    static get routes(): Router {
        const router = Router();
        // Definir todos mis rutas principales
        router.get('/', (req, res) => {
            res.send({
                message: "Hola Papus"
            })
        });

        router.use('/color', ColorRoutes.routes)
        router.use('/vehiculo', VehiculoRoutes.routes)
        return router;
    }
}