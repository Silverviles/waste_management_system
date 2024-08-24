/**
 * This file is used to create the redux store and export it to be used in the application
 */

import {combineReducers} from "redux";
import storage from "redux-persist/lib/storage";
import {persistReducer, persistStore} from "redux-persist";
import {configureStore} from "@reduxjs/toolkit";

import userReducer from "./slices/userSlice";

const rootReducer = combineReducers({
    user:userReducer
});

//create a persist reducer configuration
const persisConfig = {
    key: "user",
    version: 1,
    storage,
}

//create a persisted reducer
const persistedReducer = persistReducer(persisConfig, rootReducer);

export const store = configureStore({
    reducer: persistedReducer,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    }),
});

export const persistor = persistStore(store);