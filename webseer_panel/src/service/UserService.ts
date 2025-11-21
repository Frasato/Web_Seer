import type { User } from "../type/user";

export const allUsers = async(): Promise<User[]> =>{
    const response = await fetch("http://localhost:8080/login", {
        method: "GET",
    });

    const data: User[] = await response.json();
    return data;
}