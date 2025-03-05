import { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../App';

const PrivateRoute = ({ element }: any) => {
  const token = useContext(AuthContext);
  return token ? element : <Navigate to="/login" />;
};

export default PrivateRoute;