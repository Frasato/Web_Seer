import type { Web } from "./web";

export interface User{
    id: string,
    name: string,
    passwor: string,
    webList: Web[]
}