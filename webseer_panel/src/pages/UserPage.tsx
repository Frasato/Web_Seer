import { useEffect, useState, type ChangeEvent } from "react";
import { Link, useParams } from "react-router";
import { getWebByUsar } from "../service/WebService";
import type { Web } from "../type/web";

const UserPage = () =>{

    const {id} = useParams();
    const [urls, setUrls] = useState<Web[]>([]);
    const [searchUrls, setSearchUrls] = useState<Web[]>([]);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(()=>{
        const fetchUrls = async () =>{
            if(id === '' || id == undefined) throw new Error("ID does not exist");
            const data = await getWebByUsar(id);
            setUrls(data);
            setSearchUrls(data);
        }

        fetchUrls();
    },[]);

    const HandleSearch = (e: ChangeEvent<HTMLInputElement>) =>{
        const keyWord = e.target.value;
        setSearchTerm(keyWord);

        if(!keyWord.trim()){
            setSearchUrls(urls);
            return;
        }

        const lowerKeyWord = keyWord.toLocaleLowerCase();

        const filtered = searchUrls.filter((item)=>{
            return item.url.toLocaleLowerCase().includes(lowerKeyWord);
        });

        setSearchUrls(filtered);
    }

    return(
        <main className="user-page">
            <Link to="/" className="back-link">Retornar</Link>

            <div className="search-wrapper">
            <input
                type="text"
                placeholder="Pesquisar por URL..."
                onChange={HandleSearch}
                value={searchTerm}
            />
            </div>

            {searchUrls.map((item, index) => (
            <div key={index} className="result-card">
                <a href={item.url} target="_blank" rel="noreferrer">
                {item.url}
                </a>
                <h1>Último Acesso: {item.lastAccess}</h1>
                <h2>Número de Acessos: {item.numberOfAccess}</h2>
                <h2>Modo: {item.mode}</h2>
            </div>
            ))}
        </main>
    )
}

export default UserPage;