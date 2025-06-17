    import { useState } from "react";
    import { useNavigate } from "react-router-dom";
    import { parseJwt } from "../../utils/jwtUtils";
    import api from '../../services/axiosConfig';
    import "../Login/login.css";

    export default function Login( {setUsuario, setTipoUsuario}) {
      const navigate = useNavigate();
      const [login, setLogin] = useState("");
      const [senha, setSenha] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
          const res = await api.post("/api/login", { login, senha });
          const token = res.data;
          localStorage.setItem("token", token);

          const payload = parseJwt(token);
          console.log("Payload do token JWT:", payload);    
          localStorage.setItem("usuarioLogado", payload.sub);
          localStorage.setItem("tipo", payload.role);
          localStorage.setItem("userId", payload.id);

          // Atualiza o estado global imediatamente
          setUsuario(payload.sub);
          setTipoUsuario(payload.role);

          navigate("/consultas/all");
        } catch (err) {
          alert("Erro ao fazer login");
        }
      };

      return (
        <main className="login-content">
          <div className="login-container">
            <form className="form-login" onSubmit={handleSubmit}>
              <h2>Entrar</h2>
              <label htmlFor="login">Login</label>
              <input
                name="login"
                type="text"
                required
                id="login"
                placeholder="Username aqui..."
                value={login}
                onChange={(e) => setLogin(e.target.value)}
              />

              <label htmlFor="senha">Senha</label>
              <input
                name="senha"
                type="password"
                required
                id="senha"
                placeholder="Senha..."
                value={senha}
                onChange={(e) => setSenha(e.target.value)}
              />

              <button className="button-primary">Entrar</button>
            </form>
          </div>
        </main>
      );
    }
