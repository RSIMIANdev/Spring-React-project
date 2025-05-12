import RegisterForm from './components/RegisterForm';
import LoginForm from './components/LoginForm';

function App() {
    return (
        <div style={{
            background: 'linear-gradient(to right, #000428, #004e92)',
            color: 'white',
            fontFamily: 'Arial, sans-serif',
            minHeight: '100vh',
            padding: '20px'
        }}>
            <div style={{ textAlign: 'center' }}>
                <img
                    src="/images/roman-reigns.webp"
                    alt="Roman Reigns"
                    style={{ maxWidth: '50%', borderRadius: '12px' }}
                />
                <h1 style={{ fontSize: '2rem', marginBottom: '10px', letterSpacing: '1px' }}>OOP Auth</h1>
            </div>
            <div style={{
                background: 'rgba(255, 255, 255, 0.1)',
                padding: '20px',
                borderRadius: '12px',
                maxWidth: '400px',
                margin: '0 auto',
                boxShadow: '0 0 15px rgba(0, 0, 0, 0.5)'
            }}>
                <RegisterForm />
                <hr style={{ borderColor: 'white' }} />
                <LoginForm />
            </div>
        </div>
    );
}

export default App;
