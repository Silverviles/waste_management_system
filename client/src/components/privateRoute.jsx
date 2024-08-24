import {useSelector} from "react-redux";
import {Navigate, Outlet} from "react-router-dom";


/**
 * This is the PrivateRoute component which is used to check if the user is logged in or not
 * @returns {JSX.Element}
 * @constructor
 */
export default function PrivateRoute(){
    const currentUser = useSelector((state) => state.user.currentUser);
    return currentUser? <Outlet/>: <Navigate to='/'/>;
}