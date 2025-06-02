import './header.css'
import { Link } from "react-router-dom";

import logo from '../../assets/logo.png';

export default function Header(){
    return (
        <>
          <header>
            <div className='logo'>
                <img src={logo} alt='Logo health hands, mão segurando um simbolo de saúde' className='logo'/>
                <h2>health hands</h2>
            </div>
           

            <nav>
                <Link to="/" className="inicio">Inicio</Link> {/* leva para a pagina inicial */}
                <Link to="/appointments" className="minhas-consultas">Minhas Consultas</Link> {/* leva para a página de consultas */}
            </nav>
            
        </header>
        
        </>
    )
}