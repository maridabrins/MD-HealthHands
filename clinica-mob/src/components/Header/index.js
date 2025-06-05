
export default function Header(){

    const [fontsLoaded] = useFonts({
    'Inter-Regular': require('../../../assets/fonts/Inter_18pt-Regular.ttf'),
    'Inter-Medium': require('../../../assets/fonts/Inter_18pt-Medium.ttf'),
    'Inter-SemiBold': require('../../../assets/fonts/Inter_18pt-Semibold.ttf'),
    'Inter-Bold': require('../../../assets/fonts/Inter_18pt-Bold.ttf'),
    'Inter-ExtraBold': require('../../../assets/fonts/Inter_18pt-ExtraBold.ttf')
});

  if (!fontsLoaded) {
  }
}