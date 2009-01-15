Name:          HarperHallUtil
Version:       0.01
Release:       1
Packager:      lutz.behnke@haw-hamburg.de
Summary:       Java Utility Classes from harper-hall.de
License:       GPL
Group:         Development/Libraries
URL:           http://users.informatik.haw-hamburg.de/projects/timadorus/
BuildRoot:     %{_tmppath}/%{name}-root
BuildRequires: cobertura >= 1.9
BuildRequires: ant
BuildRequires: ant-junit
Requires: jvm >= 6.0
Requires: perl(SVN::Core)
Source0: %{name}-src-%{version}.tar.gz

%description
HarperHallUtil is a collection of utility functions and classes that have 
accumulated for the code developed under the package heading of de.harper-hall
and have not been found elsewhere.

In truth it is Lutz Behnkes little toolchest.

%prep
%setup

%build
ant test_java
ant distjar

%clean
rm -rf $RPM_BUILD_ROOT
%install


%files
/usr/share/java/bookkeeper/bookkeeper-%{version}.jar

%changelog
* Thu Jan 15 2009 lutz.behnke@haw-hamburg.de
- Specfile started

