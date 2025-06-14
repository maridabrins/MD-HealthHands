import {View, Text, TouchableOpacity, StyleSheet, SafeAreaView} from "react-native"


export default function Header(){
  const [fontsLoaded] = useFonts({
    'Inter-Regular': require('../../../assets/fonts/Inter_18pt-Regular.ttf'),
    'Inter-Medium': require('../../../assets/fonts/Inter_18pt-Medium.ttf'),
    'Inter-SemiBold': require('../../../assets/fonts/Inter_18pt-Semibold.ttf'),
    'Inter-Bold': require('../../../assets/fonts/Inter_18pt-Bold.ttf'),
    'Inter-ExtraBold': require('../../../assets/fonts/Inter_18pt-ExtraBold.ttf')
});

    if (!fontsLoaded) {
    return null; 
  }

  const handleMenuPress = () => {
    console.log("Menu pressionado");
  };
return(
  <>

  <View style={styles.header}>
      <Image style={styles.logo} source={ { uri : '../../../assets/images/logo.png'}}></Image>
      <Text style={styles.logoName}>health hands</Text>

      <TouchableOpacity style={styles.menu} onPress={onclick}>
        <Text>-</Text>
        <Text>-</Text>
        <Text>-</Text>
      </TouchableOpacity>
  </View>

  </>

)
    
}

const styles = StyleSheet.create({
  header: {
    display: 'flex',
    justifyContent: 'center',
  },
  logo: {
    width: 78,
    height: 78
  },
  logoName: {
    fontFamily: 'Inter-ExtraBold',
    fontSize: 24,
    textTransform: 'uppercase'
  },
  menu : {
   justifyContent: 'center',
   alignItems: 'center'

  }

})
