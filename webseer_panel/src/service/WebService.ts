import type { Web } from "../type/web";

export const getWebByUsar = async(id: string): Promise<Web[]> =>{
    const response = await fetch(`http://localhost:8080/url/${id}`, {method: "GET"});

    const data: Web[] = await response.json();
    return data;
}