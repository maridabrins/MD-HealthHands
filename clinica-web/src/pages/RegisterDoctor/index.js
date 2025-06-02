import "./registerDoctor.css"




import medico from '../../assets/medico.svg';



export default function RegisterDoctor() {
  return (
    <>
    <main>
      <form>
        <h2>Cadastro de Médico</h2>

        <label for='Nome'>Nome</label>
        <input name='nome' type='text' required id="nome" placeholder="Digite o nome aqui..."></input>

        <label for='crm'>CRM</label>
        <input name='crm' type='number' required id="crm" placeholder=" XX.XXX-X"></input>

        <label for='tel'>Telefone</label>
        <input name='tel' type='tel' required id="tel" placeholder="(x) 11111-1111"></input>

        <label for='email'>E-mail</label>
        <input name='email' type='email' required id="email" placeholder="email@gmail.com"></input>

        <label for='especialidade'>Especialidade</label>
        <select>
          <option value= 'pediatria'>Pediatria</option>
          <option value= 'cardiologia'>Cardiologia</option>
          <option value= 'dermatologia'>Dermatologia</option>
          <option value= 'ortopedia'>Ortopedia</option>
          <option value= 'neurologia'>Neurologia</option>
          <option value= 'otorrinolaringologista'>Otorrinolaringologista</option>
          <option value= 'oftamologia'>Oftamologia</option>

        </select>

        <div className="form-button">
          <button className="button-primary">Cadastre-se → </button>
        </div>
      </form>

      <div className="paciente-content">
        <img src= {medico} alt="homem branco sentado com jaleco e estetoscópio" ></img>
      </div>
    </main>
    
    </>
  );
}