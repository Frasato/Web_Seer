import { createBrowserRouter } from "react-router";
import App from "../App";
import Home from "../pages/Home";
import UserPage from "../pages/UserPage";

export const router = createBrowserRouter([
    {
        path: '/',
        element: <App />,
        children: [
            {path: '', element: <Home />},
            {path: '/user/:id', element: <UserPage />}
        ]
    }
]);