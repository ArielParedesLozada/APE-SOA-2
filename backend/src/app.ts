import { AppRoutes } from "./presentation/routes";
import { Server } from "./presentation/server";
import { envs } from "./config/envs";
import { MySQLDatabase } from "./data/mysql/mysql.database";
import Models from "./data/models";

(() => {
    main();
})()


async function main() {
    const database = MySQLDatabase.getInstance({
        database: envs.MYSQL_DB,
        password: envs.MYSQL_PASSWORD ?? '',
        port: envs.MYSQL_PORT,
        username: envs.MYSQL_USER,
        entities: Models
    })

    await database.connect()

    new Server({
        port: envs.PORT,
        routes: AppRoutes.routes
    })
        .start();
}