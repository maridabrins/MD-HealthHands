import { StatusBar } from 'expo-status-bar';
import { SafeAreaView, StyleSheet, Text, View } from 'react-native';

import Header from './src/components/Header/index'

export default function App() {
  return (
<SafeAreaView>
  <Header/>
</SafeAreaView>
  );
}

const styles = StyleSheet.create({
  
});
