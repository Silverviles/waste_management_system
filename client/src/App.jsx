import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/header";
import PrivateRoute from "./components/privateRoute.jsx";
import Home from "./pages/Home.jsx";

export default function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
          <Route path="/" element={<Home/>}/>
          {/*   private routes   */}
          <Route element={<PrivateRoute/>}>
          </Route>
      </Routes>
    </BrowserRouter>
  );
}
