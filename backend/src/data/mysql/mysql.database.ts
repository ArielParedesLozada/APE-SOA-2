import { DataSource } from "typeorm";
import { IDatabase } from "../database.datasource";

interface Options {
    database: string,
    port: number,
    username: string,
    password: string,
    entities: Function[]
}

export class MySQLDatabase extends IDatabase {
    public dataSource?: DataSource
    private database: string
    private port: number
    private username: string
    private password: string
    private entities: Function[]
    private static instance: MySQLDatabase

    private constructor(options: Options) {
        super()
        const { database, password, port, username, entities } = options
        this.database = database
        this.port = port
        this.password = password
        this.username = username
        this.entities = entities
    }

    public static getInstance(options?: Options) {
        if (this.instance == null) {
            if (!options) {
                throw new Error("No hay base inicializada y se necesitan parametros")
            }
            this.instance = new MySQLDatabase(options)
        }
        return this.instance
    }

    public async connect(): Promise<boolean> {
        try {
            this.dataSource = new DataSource({
                type: 'mysql',
                database: this.database,
                port: this.port,
                username: this.username,
                password: this.password,
                entities: this.entities,
                synchronize: false, //dev
                logging: true, //dev
            })
            await this.dataSource.initialize()
            return true
        } catch (error) {
            console.log('Error de conexion')
            throw error
        }
    }

}