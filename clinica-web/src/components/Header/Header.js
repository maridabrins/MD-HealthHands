import './header.css';
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import logo from '../../assets/logo.png';
import paciente from '../../assets/icon-paciente.svg'
import medico from '../../assets/icon-medico.svg'
import logoutIcon from '../../assets/logout.png'

export default function Header({usuario, tipoUsuario, setUsuario}) {
  const navigate = useNavigate();
  const [mostrarModal, setMostrarModal] = useState(false);

  const toggleModal = () => {
    setMostrarModal(!mostrarModal);
  };

  

const handleLogout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("usuarioLogado");
  localStorage.removeItem("tipoUsuario");
  setUsuario("");        // limpa o estado local para remover o nome da UI
  navigate("/choose-user");
};
  return (
    <>
      <header>
        <div className='logo'>
          <img src={logo} alt='Logo health hands, mão segurando um símbolo de saúde' className='logo' />
          <h2>health hands</h2>
        </div>

        <nav>
          <Link to="/" className="inicio">Início</Link>
          <Link to="/consultas/all" className="minhas-consultas">Minhas Consultas</Link>
          
        </nav>

        {usuario && (
          <div className="usuario-info">
            <img 
              src={tipoUsuario === "MEDICO" ? medico : paciente} 
              alt="Ícone do usuário" 
              className='foto-perfil' 
              onClick={toggleModal}
            />
            {mostrarModal && (
              <div className="modal-usuario">
                <span>{usuario}</span>
                <div className="line"></div>
                <span>{tipoUsuario === "MEDICO" ? "Médico" : "Paciente"}</span>
                <div className="line"></div>
                <button onClick={handleLogout}>
                  <img src={logoutIcon} alt="Sair" className='logoutIcon' />
                  Sair
                </button>
            </div>
          )}
          </div>
        )}
      </header>
    </>
  );
}
