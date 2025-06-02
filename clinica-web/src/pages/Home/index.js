import "./home.css"

import { Link } from 'react-router-dom';
import maoDadas from '../../assets/maosDadas.svg';

export default function Home() {
  return (
    <>
    <main>
        <div className="welcome">
            <h1>SEJA BEM VINDO A HEALTH HANDS</h1>
            <p>Nosso objetivo é levar a saúde à palma da mão de nossos pacientes. Servir e cuidar com excelência é o nosso lema. Venha descobrir como é o verdadeiro cuidado. Junte-se a nós!</p>
        <div className="welcome-button">
            <Link to="/choose-user" className="button-primary">Venha fazer parte!</Link>
        </div>
        </div>
        <div className="main-image">
            <img src= {maoDadas} alt='imagem de mãos dadas '></img>
        </div>
    </main>
    <footer>
        <div className="main-end">
            
        </div>
    </footer>
    
    </>
  );
}