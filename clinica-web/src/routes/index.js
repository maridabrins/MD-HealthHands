
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from '../pages/Home/index.js'
import ChooseUser from '../pages/ChooseUser/index.js'
import RegisterDoctor from '../pages/RegisterDoctor/index.js'
import RegisterPatient from '../pages/RegisterPatient/index.js'
import Appointments from '../pages/Appointments/index.js'
import NotFound from '../pages/NotFound'

import Header from '../components/Header/Header.js';
import '../App.css';

function AppRoutes() {
  return (
    <>
   
    <Router>
       <Header/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/choose-user" element={<ChooseUser />} />
        <Route path="/register-doctor" element={<RegisterDoctor />} />
        <Route path="/register-patient" element={<RegisterPatient />} />
        <Route path="/appointments" element={<Appointments />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router> 
    </>
  );
}

export default AppRoutes;