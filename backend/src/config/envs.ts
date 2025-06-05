import 'dotenv/config';
import { get } from 'env-var';


export const envs = {
    PORT: get('PORT').required().asPortNumber(),
    SQLITE_DB: get('SQLITE_DB').required().asString(), 
}