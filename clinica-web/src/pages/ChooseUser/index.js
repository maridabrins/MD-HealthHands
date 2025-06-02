import './chooseUser.css'
import { Link } from 'react-router-dom';
import Paciente from '../../assets/icon-paciente.svg';
import Medico from '../../assets/icon-medico.svg';

export default function ChooseUser() {
  return (
    <>
      <div className='user-container'>
        <div className='user-content'>
          <h2>Venha fazer parte</h2>
          <div className='user-cards'>

            <Link to="/register-patient" className='card-link'>
              <div className='card-infos'>
                <img src={Paciente} alt='icone homem de cabelo preto' />
                <p>Paciente</p>
              </div>
            </Link>

            <Link to="/register-doctor" className='card-link'>
              <div className='card-infos'>
                <img src={Medico} alt='icone homem de cabelo preto com jaleco e estetoscópio' />
                <p>Médico</p>
              </div>
            </Link>

          </div>
        </div>
      </div>
    </>
  );
}
