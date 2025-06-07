import { Router } from "express";
import { ColorRoutes } from "./routes/colores/routes";

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

        router.use('/waza', ColorRoutes.routes)
        return router;
    }
}