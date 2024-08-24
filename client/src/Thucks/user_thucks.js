import { loginFailure, loginStart, loginSuccess } from "../utils/redux/slices/userSlice.js";
import api from "../common/api.js";

export const loginUser = (loginData) => async (dispatch) => {
    dispatch(loginStart());

    try {
        const response = await api.post("user/login", loginData, {
            withCredentials: true,
            headers: {
                "Content-Type": "application/json",
            },
        });
        dispatch(loginSuccess(response.data));
    } catch (error) {
        dispatch(
            loginFailure(error.response?.data || "An error occurred while logging in")
        );
    }
};

export const logoutUser = () => async (dispatch) => {
    try{
        localStorage.removeItem("user");
        dispatch(logoutUser());
    }catch (error){
        console.log(error);
    }
}