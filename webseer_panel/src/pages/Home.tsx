import { useEffect, useState } from "react";
import type { User } from "../type/user";
import { allUsers } from "../service/UserService";
import { Link } from "react-router";

const Home = () =>{

    const [users, setUsers] = useState<User[]>([]);
    
      useEffect(()=>{
        const fetchUsers = async() =>{
          setUsers(await allUsers());
        }
    
        fetchUsers();
      },[]);

    return(
        <main>
          <h1>Web Seer</h1>
          {users.map((item, index) => (
            <Link to={`/user/${item.id}`} key={index}>
              <div className="card">
                <h1 className="user-card-title">{item.name}</h1>
                <h2 className="user-card-subtitle">ID: {item.id}</h2>
              </div>
            </Link>
          ))}
      </main>
    );
}

export default Home;