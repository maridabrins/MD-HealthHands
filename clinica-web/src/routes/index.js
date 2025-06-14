
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from '../pages/Home/index.js'
import ChooseUser from '../pages/ChooseUser/index.js'
import RegisterDoctor from '../pages/RegisterDoctor/index.js'
import RegisterPatient from '../pages/RegisterPatient/index.js'
import Appointments from '../pages/Appointments/index.js'
import NotFound from '../pages/NotFound'
import ScheduleAppointment from '../components/Modal/modal.js'
import Login from '../pages/Login/index.js'
import { useState } from 'react';
import Header from '../components/Header/Header.js';
import '../App.css';
import AppointmentsPatient from '../pages/AppointmentsPatient/index.js';

function AppRoutes() {
  const [usuarioLogado, setUsuarioLogado] = useState(localStorage.getItem("usuarioLogado") || "");
  const [tipoUsuario, setTipoUsuario] = useState(localStorage.getItem("tipoUsuario") || "");

  return (
    <>
   
    <Router>
       <Header 
        usuario={usuarioLogado} 
        tipoUsuario={tipoUsuario} 
        setUsuario={setUsuarioLogado}
      />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/choose-user" element={<ChooseUser />} />
        <Route path="/medico/create" element={<RegisterDoctor />} />
        <Route path="/paciente/create" element={<RegisterPatient />} />
        <Route path="/consultas/all" element={<Appointments />} />
        <Route path="/consultas/agendar" element={< ScheduleAppointment />} /> 
        {/* <Route path="/consultas/update/**" element={< UpdateAppointment />} /> 
        <Route path="/consultas/delete/**" element={< DeleteAppointment />} /> 
        <Route path="/consultas/buscar/" element={< SearchAppointment />} /> */}
        <Route path="/api/login" element={<Login 
            setUsuario={setUsuarioLogado} 
            setTipoUsuario={setTipoUsuario}
          />} />  

        <Route path="/appointments-patient" element={<AppointmentsPatient />} />

        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router> 
    </>
  );
}

export default AppRoutes;