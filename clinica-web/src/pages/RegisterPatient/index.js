import "./registerPatient.css"


import 'react-toastify/dist/ReactToastify.css';

import paciente from '../../assets/paciente.svg';



export default function RegisterPatient() {
  return (
    <>
    <main>
      <form>
        <h2>Cadastro de paciente</h2>

        <label for='Nome'>Nome</label>
        <input name='nome' type='text' required id="nome" placeholder="Digite o nome aqui..."></input>

        <label for='tel'>Telefone</label>
        <input name='tel' type='tel' required id="tel" placeholder="(x) 11111-1111"></input>

        <label for='email'>E-mail</label>
        <input name='email' type='email' required id="email" placeholder="email@gmail.com"></input>

        <label for='dataNasc'>Data de nascimento</label>
        <input name='dataNasc' type='date' required id="dataNasc" ></input>

        <div className="form-button">
          <button className="button-primary">Cadastre-se → </button>
        </div>
      </form>

      <div className="paciente-content">
        <img src= {paciente} alt="mulher branca de olho azul usando mascara" ></img>
      </div>
    </main>
    
    </>
  );
}